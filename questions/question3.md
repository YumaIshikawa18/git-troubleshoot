# Question 3: git merge（一括統合）
後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標
- mergeによる複数コミットの一括取り込み方法を身に着ける
- cherry-pickとmergeの使い分けを理解する

## 制約条件
- 使用必須コマンド: `git merge`（マージにはオプションがあるので、余裕があれば調べてみること）
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備
`fix-attempts`タグの状態からスタートするために、以下のbashコマンドを入力してください。
```bash
git checkout question3
git checkout -b question3-{チームの番号}
```
現状では、ゲームは動作しますが、ヒント表示とボーナス機能、グッドラックメッセージが不足しています。
masterブランチには、これら全ての機能が既に実装済みなので、masterの内容をまとめて反映したいです。

## 現在の状態

```java
// src/main/java/com/yuzukiku/Main.java の現在の状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100;
    int maxAttempts = 5;

    System.out.println("Welcome to GuessGame!");
    // ヒント表示なし
    // グッドラックメッセージなし

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
    // ボーナス機能なし
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
    System.out.println("Good luck!");  // ← 追加
    System.out.println("⚡ [Hint] The secret is in [1–" + maxNumber + "] ⚡"); // ← 追加

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

    // --- BONUS ROUND ---
    try (Scanner bonus = new Scanner(new java.io.File("bonus.txt"))) {
      while (bonus.hasNextLine()) {
        System.out.println(bonus.nextLine());
      }
    } catch (IOException e) {
      System.out.println("No bonus available.");
    }
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
# - Welcome メッセージの直後に"Good luck!"が表示される
# - ヒントが表示される
# - ゲーム終了後にボーナスメッセージが表示される
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. question3/{チームの番号}ブランチに移動のうえ、現在の状況を把握（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log --oneline --all --graph
   ```
2. masterブランチの最新状態を確認（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log origin/master --oneline
   ```

3. masterをmergeで一括取り込み（ここはCLIを使う）

4. merge後に動作確認を行う

cherry-pickは個別のコミットを選択的に取り込むのに対し、mergeは指定したブランチの全ての変更を一括で統合します。

</details>

<details>
<summary>解答例</summary>

```bash
# masterブランチから全ての不足している機能を一括で取り込む（オプションはなくてもOK）
git merge origin/master --no-ff -m "Merge all remaining features from master"
```

</details>