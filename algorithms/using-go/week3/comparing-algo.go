package main

func main() {

}

// The algorithm
// a) looks at the elements starting from the min index to the max index
// b) looks for the max in table
// c) as second value always returns the smaller index for element x, if x is in table
// d) sorts table
// e) returns all indexes for x
// f) looks at all elements in table
// g) is correct only if table is sorted
// h) at the end always returns false, -1

// a) algoX
// b) no
// c) no
// d) no
// e) no
// f) algoX
// g) algoY
// h) no

// linear search
func algoX(table []int, x int) (bool, int) {
	for i, el := range table {
		if el == x {
			return true, i
		}
	}
	return false, -1
}

// linear search that accumulates all indexes for x
// the change is easy and does not change the time complexity
// of the algorithm, space complexity is O(n) as in the worst case all
// elements in table are equal to x
func algoXAcc(table []int, x int) (bool, []int) {
	indexes := []int{}
	for _, el := range table {
		if el == x {
			indexes = append(indexes, x)
		}
	}
	return len(indexes) > 0, indexes
}

// binary search
func algoY(table []int, x int) (bool, int) {
	low, high := 0, len(table)-1

	for low <= high {
		mid := (low + high) / 2
		if table[mid] < x {
			low = mid + 1
		} else if table[mid] > x {
			high = mid - 1
		} else {
			return true, mid
		}
	}
	return false, -1
}

// binary search that accumulates all indexes for x
// is way more difficult to implement and changes the time complexity
// because we have to look for possible equal values near the found index

// when searching for an element in table
// if table is sorted algoY is more efficient than algoX because
// it halves to portions to look into at each iteration
