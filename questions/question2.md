# Question 2: git rebase -i（インタラクティブリベース）

## 学習目標

- インタラクティブリベースによるコミット履歴の整理方法を身に着ける
- squashによる複数コミットの統合を実践

## 制約条件

- 使用必須コマンド: `git rebase -i` `git cherry-pick`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 現在の状況

`fix-attempts`タグの状態からスタートするために、以下のbashコマンドを入力してください。

```bash
git checkout question2
<<<<<<< HEAD
git checkout -b question2-{チームの番号}
=======
git checkout -b question2/{チームの番号}
>>>>>>> 5dab15a (fix: ブランチ名を修正)
```

現状では、ゲームは動作しますが、ターミナル上にヒントが表示されません。
以下の場所に、ヒントを表示するコードを追加したいです。

```java
// src/Main.java の現在の状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100;
    int maxAttempts = 5;

    System.out.println("Welcome to GuessGame!");
    // ↑ この後にヒント表示を追加したい

    Random rand = new Random();
    int secret  = rand.nextInt(maxNumber) + 1;
    Scanner sc  = new Scanner(System.in);

    for (int i = 1; i <= maxAttempts; i++) {
      System.out.print("Try #" + i + ": ");
      int guess = Integer.parseInt(sc.nextLine());
      if (guess == secret) {
        System.out.println("🎉 Correct!");
        return;
      }
      System.out.println(guess < secret ? "Too low!" : "Too high!");
    }

    System.out.println("Game over. The number was: " + secret);
  }
}
```

## 期待する最終状態

```java
// 最終的に期待する状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100;
    int maxAttempts = 5;

    System.out.println("Welcome to GuessGame!");
    System.out.println("⚡ [Hint] The secret is in [1—" + maxNumber + "] ⚡");
    // ↑ 2つのWIPコミットを1つにまとめた結果

    Random rand = new Random();
    // ... 以下は変更なし
  }
}
```

## 動作確認方法

```bash
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar
# 期待する動作:
# - Welcome メッセージの直後にヒントが表示される
# - ヒントの書式が整理されている
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. まずmasterにあるヒント関連の2つのコミットを取り込む

2. 履歴を確認してHEADから2つのコミットを対象にinteractive rebaseを実行

3. エディタで2つ目のコミットを`squash`に変更

</details>

<details>
<summary>解答例</summary>

```bash

# 1. hint関連の2つのコミットを取り込む
git cherry-pick 78f62bf313f37f2a9478a4b77b6c7600e2d1f2ab 581b7da7d2bd32b6205a7b1d053342bb423eff34


# 2. インタラクティブリベースで2つのコミットを統合
git rebase -i HEAD~2
# エディタで以下のように変更:
# pick <hint-v1のハッシュ> add hint v1
# squash <hint-formatのハッシュ> refine hint formatting

# 3. コミットメッセージを編集（デフォルトでOKなら保存して終了）

# 4. 動作確認
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar

# 5. 履歴確認（1つのコミットにまとまっているか）
git log --oneline
```

</details>
