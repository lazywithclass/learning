## Learning algorithms and data structures using Go

### How do I

#### Read CLI arguments

```Go
num, _ := strconv.Atoi(os.Args[1])
```

#### Read until end of line

```Go
scanner := bufio.NewScanner(os.Stdin)
scanner.Scan()
line := scanner.Text()
```

#### Read words until CTRL+d

```Go
scanner := bufio.NewScanner(os.Stdin)
scanner.Split(bufio.ScanWords)
for scanner.Scan() {
    token := scanner.Text()
}
```