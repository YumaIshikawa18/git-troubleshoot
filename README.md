# git-troubleshoot

Gitのコマンドライン操作を実践的に学習するためのハンズオントレーニングリポジトリです。数当てゲームを題材に、`git cherry-pick`、`git rebase`、`git reset`などの実用的なGitコマンドを習得できます。

## 📚 目的

CLIを使ったGit操作をスムーズに行えるようになることを目指します。

### なぜCLI操作を学ぶべきか？

1. **GUIの変更に惑わされない** - GUIのアップデートや開発環境の変更に強くなる
2. **効率的な作業が可能** - GUIは毎回同じ操作が必要だが、コマンドは実行履歴をそのまま使える

## 🎯 対象者

- GitのGUI操作は使えるが、CLIでの操作に不安がある方
- `git cherry-pick`や`git rebase`などの高度なGitコマンドを実践的に学びたい方
- チーム開発でGitのトラブルシューティングができるようになりたい方

## 📋 前提条件

- **Java**: JDK 21以上
- **Git**: 2.x以上
- **環境**: Linux、macOS、またはWindows（WSL推奨）

### Javaバージョンの確認

```bash
java --version
```

JDK 21以上がインストールされていない場合は、[OpenJDK](https://openjdk.org/)または[Oracle JDK](https://www.oracle.com/java/technologies/downloads/)からダウンロードしてください。

## 🚀 クイックスタート

### 1. リポジトリのクローン

```bash
git clone https://github.com/YumaIshikawa18/git-troubleshoot.git
cd git-troubleshoot
```

### 2. アプリケーションの起動確認

```bash
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar
```

正常に動作すれば、数当てゲームが起動します。

### 3. 学習の開始

`questions/introduction.md`を開いて、学習の全体像を把握してください。その後、`question0.md`から順番に問題に取り組んでください。

## 📁 プロジェクト構成

```
git-troubleshoot/
├── README.md                 # このファイル
├── pom.xml                   # Mavenプロジェクト設定
├── src/
│   └── main/
│       └── java/
│           └── com/yuzukiku/
│               └── Main.java # 数当てゲームのメインクラス
├── questions/               # 学習用の問題集
│   ├── introduction.md      # 学習の目的と概要
│   ├── question0.md         # 問題0: git cherry-pick（単一コミット）
│   ├── question1.md         # 問題1: git cherry-pick（複数コミット）
│   ├── question2.md         # 問題2: git rebase -i
│   ├── question3.md         # 問題3: git reset
│   ├── question4.md         # 問題4: 応用問題
│   └── question5.md         # 問題5: 総合問題
├── mvnw                     # Maven Wrapper（Unix系）
└── mvnw.cmd                 # Maven Wrapper（Windows）
```

## 📖 学習の進め方

### ステップ1: 問題の確認

`questions/`ディレクトリ内のマークダウンファイルを**必ずプレビューモードで**確認してください。生のコードで見るとヒントと解答が見えてしまいます。

### ステップ2: ブランチの準備

各問題では、専用のブランチを作成します。

```bash
git checkout question0
git checkout -b question0-{チームの番号}
```

### ステップ3: 問題に取り組む

各問題の指示に従って、Gitコマンドを使用してコードを修正します。

**重要な制約条件:**
- 指定されたGitコマンドを使用する（手動でのコード編集は禁止）
- GUIツールの使用は禁止（コミット履歴の確認などはGUIでもOK）

### ステップ4: 動作確認

コードを修正したら、必ず動作確認を行います。

```bash
./mvnw clean package && java -jar target/guessgame-1.0-SNAPSHOT.jar
```

### ステップ5: 次の問題へ

問題が解けたら、次の問題に進みます。

## 🎮 数当てゲームについて

このリポジトリでは、シンプルな数当てゲームを題材にしています。

### ゲームのルール

1. システムがランダムに数字を選択
2. プレイヤーは制限回数内に数字を当てる
3. 予想が外れた場合、「Too high!」または「Too low!」のヒントが表示される

### ゲームのパラメータ

- **最大値**: 100（設定により変更可能）
- **試行回数**: 5回（設定により変更可能）

## 🔧 トラブルシューティング

### Mavenのビルドが失敗する

```bash
# Maven Wrapperに実行権限を付与
chmod +x mvnw
```

### Javaのバージョンが古い

JDK 21以上が必要です。以下のコマンドでバージョンを確認してください。

```bash
java --version
```

### ゲームが起動しない

ビルド成果物が正しく生成されているか確認してください。

```bash
ls -la target/guessgame-1.0-SNAPSHOT.jar
```

## 💡 ヒント

- 各問題には詳細なヒントと解答例が用意されています
- 詰まった場合は、問題ファイル内の`<details>`セクションを開いてください
- `git log --oneline --all --graph`でコミット履歴を視覚的に確認できます

## 📚 学習できるGitコマンド

このチュートリアルを通じて、以下のGitコマンドを実践的に学習できます：

- `git cherry-pick` - 特定のコミットを選択的に取り込む
- `git rebase -i` - コミット履歴をインタラクティブに編集する
- `git reset` - コミットの取り消しや巻き戻し
- `git log` - コミット履歴の確認
- その他、実務で役立つGitの高度な操作

## 🤝 コントリビューション

このプロジェクトへの貢献を歓迎します。問題の追加、ドキュメントの改善、バグ修正など、お気軽にPull Requestを送ってください。

## 📄 ライセンス

このプロジェクトのライセンスについては、リポジトリのオーナーにお問い合わせください。

## READMEの作成について
GitHub Copilotによって生成されました。

## 🙏 謝辞

このチュートリアルは、Gitの実践的なスキルを習得したいすべての開発者のために作成されました。楽しく学習してください！

