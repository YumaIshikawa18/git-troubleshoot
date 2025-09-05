# Question 6: git rebase --onto（高度な履歴操作・発展編）
後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標
- git rebase --ontoによる柔軟な履歴操作を身に着ける
- 不要なコミットを除外した履歴の付け替えを実践する
- 実際のプロジェクトで起こりうる複雑な状況への対処法を学ぶ

## 制約条件
- 使用必須コマンド: `git rebase --onto`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備
以下のbashコマンドを入力してください。
```bash
git checkout question6
git checkout -b question6-{チームの番号}
```

## 問題の状況
現在のブランチには以下のような履歴があります：
```
question6: EXPERIMENTAL_UI -> EXPERIMENTAL_DEBUG -> IMPORTANT_1 -> IMPORTANT_2 -> IMPORTANT_3
```

しかし、実験的機能（EXPERIMENTAL_UI, EXPERIMENTAL_DEBUG）は不要になりました。
一方で、重要な修正（IMPORTANT_1, IMPORTANT_2, IMPORTANT_3）は masterの最新状態に適用したいです。

## 現在の状態

```java
// src/main/java/com/yuzukiku/Main.java の現在の状態（抜粋）
public class Main {
  public static void main(String[] args) {
    // ... hint-v1までの内容 ...
    System.out.println("[EXPERIMENTAL] Testing new UI");      // ← 除外したい
    System.out.println("[EXPERIMENTAL] Debug mode enabled");  // ← 除外したい
    System.out.println("[INFO] Enhanced game experience");    // ← 残したい
    System.out.println("[INFO] Improved user interface");     // ← 残したい  
    System.out.println("[INFO] Final optimizations complete");// ← 残したい
    
    // ... 以下省略
  }
}
```

## 期待する最終状態
```java
// 期待する修正後の状態
public class Main {
  public static void main(String[] args) {
    // ... masterの最新内容（全機能含む）...
    System.out.println("[INFO] Enhanced game experience");    // ← 移植完了
    System.out.println("[INFO] Improved user interface");     // ← 移植完了
    System.out.println("[INFO] Final optimizations complete");// ← 移植完了
    
    // ... 以下省略
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
# - 実験的機能のメッセージが表示されない
# - 重要な機能追加のメッセージが表示される
# - masterの全機能（ヒント、ボーナス、グッドラック等）も含まれている
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. question6/{チームの番号}ブランチに移動のうえ、現在の状況を把握（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log --oneline --all --graph
   ```

2. masterの最新を確認（IntellJの拡張機能を使ってもOK）:
   ```bash
   git log origin/master --oneline
   ```

3. rebase --onto で不要部分を除外（ここはCLIを使う）:
   ```bash
   # コミットハッシュを確認して実行
   git rebase --onto origin/master <EXPERIMENTAL_DEBUGのハッシュ> HEAD
   ```

4. 結果確認

**rebase --onto の構文理解**:
```bash
git rebase --onto <新しいベース> <除外したい範囲の終了> <移植したい範囲の終了>
```

実験的な2つのコミットをスキップして、重要な3つのコミットだけをmasterの上に移植します。

</details>

<details>
<summary>解答例</summary>

```bash
# 実験的コミット部分を除外して、重要コミットだけをmasterに移植
# <EXPERIMENTAL_DEBUGのハッシュ>の部分は実際のハッシュ値に置き換える
git rebase --onto origin/master <EXPERIMENTAL_DEBUGのハッシュ> HEAD

# 例: git rebase --onto origin/master abc1234 HEAD
# abc1234 = EXPERIMENTAL_DEBUG のコミットハッシュ
```

</details>