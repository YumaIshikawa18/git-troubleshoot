# Question 5: git stash & tag（一時退避とリリース管理）

後述の制約条件を守ったうえで、「現在の状態」から「期待する状態」にコードを変更してください。

## 学習目標

- git stashによる作業中変更の一時退避方法を身に着ける
- git tagによるリリース管理方法を理解する
- 実際の開発フローにおける stash の活用を実践する

## 制約条件

- 使用必須コマンド: `git stash` `git tag`
- 使用禁止: 上記コマンドのGUI操作、手動でのコード編集（githubのリポジトリ閲覧や、ネットでの情報収集はOK）

## 事前準備

以下のbashコマンドを入力して、作業中の状態を作成してください。

```bash
git checkout question5
git checkout -b question5/{チームの番号}
```

ブランチの移動が完了したら、mainメソッドの末尾に次の2行を追加してください。ステージングはしないでください。

```java
    // STASH_ME: This is a work-in-progress comment
    System.out.println("まだリリースには入れたくないコード");
```

背景：あなたは新機能の開発中ですが、緊急でREADMEを更新してv1.0.0をリリースする必要が生じました。
しかし、現在の変更はまだ未完成でコミットできません。
先ほど追加したコードは一旦退避させたうえで、リリース管理用のタグを追加し、
作業を続けたいです。

## 現在の状態

```bash
git status
# 未ステージの変更:
#   modified:   src/main/java/com/yuzukiku/Main.java
```

```java
// src/main/java/com/yuzukiku/Main.java の末尾に追加された内容
public class Main {
    public static void main(String[] args) {
        // ... 既存のコード ...

        // 末尾に以下が追加されている（未コミット状態）
        // STASH_ME: This is a work-in-progress comment
        System.out.println("まだリリースには入れたくないコード");
    }
}
```

## 期待する最終状態

1. 作業中の変更を一時退避
2. READMEファイルを更新してコミット（”数当てゲーム:v1.0.0 的な文言をREADMEに追記する”）
3. v1.0.0のリリースタグを作成
4. 退避した変更を復元
5. 作業を完了させてコミット（作業内容はv1.0.0でリリースされずに済む）

```java
// 最終的な状態
public class Main {
    public static void main(String[] args) {
        // ... 既存のコード（全機能含む）...

        // 作業完了後の状態
        // STASH_ME: This is a work-in-progress comment
        System.out.println("まだリリースには入れたくないコード);
    }
}
```

## 動作確認方法

```bash
# bonus.txtファイルを作成のうえ以下を実行
echo "CONGRATS! You've unlocked a BONUS surprise!" > bonus.txt

# 最終的なゲーム実行
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar

# タグの確認
git tag
git show v1.0.0

# 期待する動作:
# - 全機能が正常に動作する
# - v1.0.0タグが作成されている
# - 作業履歴がきれいに整理されている
```

<details>
<summary>ヒント（詰まったらチェック！）</summary>

1. question5/{チームの番号}ブランチに移動のうえ、現在の状況を把握（IntellJの拡張機能を使ってもOK）:
   ```bash
   git status
   git diff
   ```

2. 変更を一時退避:
   ```bash
   git stash push -m "Work in progress: development improvements"
   ```

3. 緊急作業（README更新の上実施）:
   ```bash
   git add README.md
   git commit -m "Update README for v1.0 release"
   ```

4. リリースタグを作成:
   ```bash
   git tag -a v1.0.0 -m "Release version 1.0.0"
   ```

5. stashした変更を復元して作業完了（ここはCLIを使う）

**stash の主要コマンド**:

- `git stash`: 変更を退避
- `git stash list`: stash一覧
- `git stash pop`: 最新stashを復元＆削除
- `git stash apply`: stashを復元（残す）←基本使わない

</details>

<details>
<summary>解答例</summary>

```bash
# 作業中の変更を一時退避(文章はなくてもいい)
git stash push -m "Work in progress: development improvements"

# 緊急作業：README更新（数当てゲーム:v1.0.0を追記）
git add README.md
git commit -m "Update README for v1.0.0 release"

# リリースタグを作成
git tag -a v1.0.0 -m "Release version 1.0.0 - Complete number guessing game"

# 退避した変更を復元
git stash pop

# 作業を完了させてコミット
git add .
git commit -m "Complete development improvements"
```

</details>
