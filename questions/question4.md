# Question 4: git reset/revert（トラブル解消）

後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標

- git resetによる履歴の巻き戻し方法を身に着ける
- git revertによる安全な変更取り消し方法を理解する
- reset と revert の使い分けを実践する

## 制約条件

- 使用必須コマンド: `git reset --hard` `git revert`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備

以下のbashコマンドを入力して、問題のあるコミットを含む状態を作成してください。

```bash
git checkout question4
git checkout -b question4/{チームの番号}
```

現在の状態では、正常なゲーム機能に加えて以下の問題があります：

1. DEBUG コミット: デバッグ用のコードが残っている
2. CHEAT コミット: 不正な機能（999で必ず当たる）が追加されている

1は、`git reset --hard` 2は、`git revert`を使って問題のあるコミットを消してください
（両方使うことで違いを理解していただく意図です）

## 現在の状態

```java
// src/main/java/com/yuzukiku/Main.java の現在の状態（一部抜粋）
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100;
    int maxAttempts = 5;

    System.out.println("Welcome to GuessGame!");
    System.out.println("Good luck!");
    System.out.println("⚡ [Hint] The secret is in [1–" + maxNumber + "] ⚡");

    Random rand = new Random();
    int secret  = rand.nextInt(maxNumber) + 1;
    System.out.println("DEBUG: secret = " + secret); // ← 削除したい（DEBUG）
    Scanner sc  = new Scanner(System.in);

    for (int i = 1; i <= maxAttempts; i++) {
      System.out.print("Try #" + i + ": ");
      int guess = Integer.parseInt(sc.nextLine());
      if (guess == 999) { System.out.println("🎉 Correct!"); return; } // ← 削除したい（CHEAT）
      if (guess == secret) {
        System.out.println("🎉 Correct!");
        return;
      }
      System.out.println(guess < secret ? "Too low!" : "Too high!");
    }

    // ... 以下省略
  }
}
```

## 期待する最終状態

```java
// 期待する修正後の状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100;
    int maxAttempts = 5;

    System.out.println("Welcome to GuessGame!");
    System.out.println("Good luck!");
    System.out.println("⚡ [Hint] The secret is in [1–" + maxNumber + "] ⚡");

    Random rand = new Random();
    int secret  = rand.nextInt(maxNumber) + 1;
    // DEBUG行は削除済み
    Scanner sc  = new Scanner(System.in);

    for (int i = 1; i <= maxAttempts; i++) {
      System.out.print("Try #" + i + ": ");
      int guess = Integer.parseInt(sc.nextLine());
      // CHEAT行は削除済み
      if (guess == secret) {
        System.out.println("🎉 Correct!");
        return;
      }
      System.out.println(guess < secret ? "Too low!" : "Too high!");
    }

    // ... 以下省略（正常な状態に戻る）
  }
}
```

## 動作確認方法

```bash
# bonus.txtファイルを作成のうえ以下を実行
echo "CONGRATS! You've unlocked a BONUS surprise!" > bonus.txt

# ゲーム実行
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar
# 期待する動作:
# - デバッグメッセージが表示されない
# - 999を入力しても当たりにならない
# - 正常なゲームとして動作する
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. question4/{チームの番号}ブランチに移動のうえ、現在の状況を把握（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log --oneline --all --graph
   ```

2. CHEAT コミットを完全に削除（履歴改変）:
   ```bash
   git reset --hard HEAD~1
   ```

3. DEBUG コミットを安全に取り消し（履歴保持）:
   ```bash
   git revert HEAD --no-edit
   ```

4. 結果確認（ここはCLIを使う）

**reset vs revert の違い**:

- `reset --hard`: コミットを履歴から完全に削除（危険だが履歴がきれい）
- `revert`: 変更を打ち消す新しいコミットを作成（安全だが履歴が複雑）

</details>

<details>
<summary>解答例</summary>

```bash
# CHEAT コミットを履歴から完全に削除
git reset --hard HEAD~1

# DEBUG コミットを安全に取り消し
git revert HEAD --no-edit
```

</details>
