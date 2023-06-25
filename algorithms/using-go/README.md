## Learning algorithms and data structures using Go

### How do I

#### Sort an array onf int

```Go
import "sort"

sort.Ints([]int{3,2,1})
```

#### Create a type

```Go
type stack []rune
s := make(stack, 0)
```

#### Read a unicode line

```Go
for _, myrune := range line {

}
```

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

#### Define new type

```Go
type listNode struct {
    item int
    next *listNode
}
```