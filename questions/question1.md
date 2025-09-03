# Question 1: git cherry-pick（複数コミットの選択的取り込み）
後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標
- 複数コミットのみを選択的に取り込む方法を身に着ける
- 範囲によるcherry-pickを理解する

## 制約条件
- 使用必須コマンド: `git cherry-pick`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備
`skeleton`タグの状態からスタートするために、以下のbashコマンドを入力してください。
```bash
<<<<<<< HEAD
git checkout skeleton
git checkout -b question1/{チームの番号}
=======
git checkout question1
git checkout -b question1-{チームの番号}
>>>>>>> e58fcd1 (Change suggestion branch name  (#7))
```
現状では、数当てゲームの最大値:50、試行回数:3 になっています。ここを 最大値:100、試行回数:5  に修正したいです。
また、ボーナス機能も不足しているため、追加したいです。

## 現在の状態

```java
// src/Main.java の現在の状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 50;  // この値を100に修正したい
    int maxAttempts = 3;   // この値を5に修正したい

    System.out.println("Welcome to GuessGame!");

    // 省略

     System.out.println("Game over. The number was: " + secret);
    // ボーナス機能なし
  }
}
```

## 期待する最終状態
```java
// 修正後の期待する状態
public class Main {
  public static void main(String[] args) {
    int maxNumber   = 100; // 修正完了
    int maxAttempts = 5;   // 修正完了

    System.out.println("Welcome to GuessGame!");
    
    // 省略
     
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
# - 数字の最大値が上がっている（今回は、6以上の当たりが観測できればOKとする）
# - 最大試行回数が5回（途中で当たった場合は再度実行）
# - ゲーム終了後にボーナスメッセージが表示される
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
# 範囲修正コミット（fix-range）と試行回数修正コミット（fix-attempts）、ボーナス機能コミット(feat-bonus-feature)を取り込む
git cherry-pick 8eeee6057f297591d66e8e7e84112984bcd086a0 8eeee6057f297591d66e8e7e84112984bcd086a0 94781085245bdd75e88e77a5528739675fd68f37
```

</details>