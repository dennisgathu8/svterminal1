# The Sovereign Terminal

A powerful terminal application built with ClojureScript, featuring GitHub integration and REPL capabilities.

## Features

- **Terminal Emulation**: Full terminal-like experience in the browser
- **Command History**: Persisted command history using localStorage
- **REPL**: Built-in ClojureScript REPL for evaluating expressions
- **GitHub Integration**: Search repositories and manage GitHub resources
- **Demos**: Interactive demos to showcase terminal capabilities
- **Theming**: Dark and light themes with customizable colors
- **Deployment Ready**: Deploy to Fly.io with automated CI/CD

## Quick Start

### Prerequisites

- [Clojure CLI](https://clojure.org/guides/getting_started)
- [Node.js](https://nodejs.org/) (v16 or higher)
- [npm](https://www.npmjs.com/) or [yarn](https://yarnpkg.com/)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/the-sovereign-terminal.git
cd the-sovereign-terminal
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

4. Open [http://localhost:8080](http://localhost:8080) in your browser.

### Building for Production

```bash
npm run build
```

The compiled assets will be in `resources/public/js/compiled/`.

## Usage

### Available Commands

| Command | Description |
|---------|-------------|
| `help` | List all available commands |
| `clear` | Clear the terminal output |
| `echo <text>` | Print text to the terminal |
| `pwd` | Print current working directory |
| `ls` | List directory contents |
| `date` | Display current date and time |
| `github <subcommand>` | GitHub integration commands |
| `demo <name>` | Run a demo by name |

### GitHub Commands

| Command | Description |
|---------|-------------|
| `github set-token <token>` | Set GitHub API token |
| `github user` | Fetch authenticated user info |
| `github search <query>` | Search repositories |

### REPL Commands

| Command | Description |
|---------|-------------|
| `eval (+ 1 2)` | Evaluate arithmetic expressions |

## Architecture

```
src/sovereign_terminal/
├── core.cljs           # Main application entry point
├── ui/
│   ├── terminal.cljs   # Terminal component
│   └── components.cljs # Reusable UI components
├── repl/
│   ├── core.cljs       # Command execution
│   └── eval.cljs       # Expression evaluation
├── github/
│   └── core.cljs      # GitHub API integration
├── history/
│   └── core.cljs      # Command history management
└── demos/
    └── core.cljs      # Demo commands
```

## Development

### Running Tests

```bash
npm test
```

### Project Structure

- `deps.edn` - Clojure dependencies
- `shadow-cljs.edn` - Shadow-cljs build configuration
- `package.json` - Node.js dependencies
- `fly.toml` - Fly.io deployment configuration
- `.github/workflows/deploy.yml` - CI/CD pipeline

## Deployment

### Deploy to Fly.io

1. Create a Fly.io account and install flyctl:
```bash
flyctl auth login
```

2. Launch the app:
```bash
flyctl launch
```

3. Deploy:
```bash
flyctl deploy
```

### GitHub Actions

The project includes a CI/CD workflow that automatically builds and deploys to Fly.io on pushes to the main branch.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [ClojureScript](https://clojurescript.org/)
- [Reagent](https://reagent-project.github.io/)
- [Shadow-cljs](https://shadow-cljs.github.io/shadow-cljs/)
- [Tokyonight Theme](https://github.com/folke/tokyonight.vim)
