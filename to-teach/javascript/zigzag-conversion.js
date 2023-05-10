// PAYPALISHIRING

function convert(str, rows) {
    let positions = []
    // fill the array
    for (let i = 0; i < str.length; i++) {
        positions[i] = []
    }
    
    let x = 0
    let y = 0
    let goingDown = true
    for (let i = 0; i < str.length; i++) {
        positions[y][x] = str[i]

        if (y == rows - 1) {
            // reached bottom
            goingDown = false
        }
        if (y == 0) {
            // reached top
            goingDown = true
        }

        if (goingDown) {
            y++
        } else {
            y--
            x++
        }
    }

    let result = ''
    for (let y = 0; y < positions.length; y++) {
        for (let x = 0; x < positions[y].length; x++) {
            if (positions[y][x]) {
                result += positions[y][x]
            }
        }
    }
    return result
}

console.log(convert('PAYPALISHIRING', 3))