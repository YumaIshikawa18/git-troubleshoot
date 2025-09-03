# Question 0: git cherry-pick（単一コミットの選択的取り込み）

後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標

- 一つのコミットを選択的に取り込む方法を身に着ける
- 特定コミットのcherry-pickを理解する

## 制約条件

- 使用必須コマンド: `git cherry-pick`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備

セットアップ状態からスタートするために、以下のbashコマンドを入力してください。

```bash
git checkout question0
git checkout -b question0/{チームの番号}
```

現状では、Hello World! が表示されるだけです。ここに初期状態のゲームロジック(skeletonタグのついたコミット)を追加したいです。

## 現在の状態

```java
// src/Main.java の現在の状態
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!"); // ここにゲームロジックを追加したい
  }
}
```

## 期待する最終状態

```java
// 期待する修正後の状態
public class Main {
  public static void main(String[] args) {
      int maxNumber   = 50;  // ← problem2 で 100 に直す
      int maxAttempts = 3;   // ← problem2 で 5 に直す

      System.out.println("Welcome to GuessGame!"); // ← problem3/problem6 で装飾・追加

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
          if (guess < secret) {
              System.out.println("Too low!");
          } else {
              System.out.println("Too high!");
          }
      }

      System.out.println("Game over. The number was: " + secret);
      // ← problem4 で bonus 機能追加
  }
}
```

## 動作確認方法

```bash
# ゲーム実行
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar
# 期待する動作:
# - 数当てゲームが遊べるようになっている
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. question1/{チームの番号}ブランチに移動のうえ、現在の状況を把握（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log --oneline --all --graph
   ```
2. masterブランチにある取り込みたいコミットを特定（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log origin/master --oneline
   ```

3. 特定したコミットを取り込む（ここはCLIを使う）

4. 各cherry-pick後に動作確認を行う

</details>

<details>
<summary>解答例</summary>

```bash
# 初期段階のゲームロジック（skeleton）を取り込む
git cherry-pick c986c93cee890a4875aedd794ad2b67a0c33ec31
```

</details>
