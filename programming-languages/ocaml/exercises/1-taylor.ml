let fact n =
    let rec loop n acc =
        if n <= 1 then acc
        else loop (n - 1) (acc * n)
    in
    loop n 1

let _ = fact 10


let sin x n =
    let rec loop approx =
        if approx = 1
        then x
        else
            let curr = (x ** (float_of_int approx)) /. (float_of_int (fact approx)) in
            if (approx - 1) mod 4 = 0
            then (sin x (approx - 2)) +. curr
            else (sin x (approx - 2)) -. curr
    in
    loop ((n * 2) + 1)

let _ = (Stdlib.sin 3., sin 3. 9)
