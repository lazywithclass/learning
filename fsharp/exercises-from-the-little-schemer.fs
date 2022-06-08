// write a function that removes the first element from that
// matches a predicate
let rec removeElementFlat pred lls =
    match lls with
        | [] -> []
        | l::ls -> if pred l then ls
                   else l :: removeElementFlat pred ls

// TODO
// the list could be made of nested lists
// how to do this in F#
let firstLvl = [
    [1;2;3];[4;5;6]
    ]

let secondLvl = [
        [
            [1;2;3]
        ];
        [
            [4;5;6]
        ];
    ]


// write a function that takes a list an element and a predicate
// it returns a new list that has the element after each element in the list
// that matches the predicate
let rec addR xs pred el =
    match xs with
        | [] -> []
        | x::xs when pred x -> x :: el :: (addR xs pred el)
        | x::xs -> x :: (addR xs pred el)


// write a function that takes a list and a predicate
// it removes all elements that match the predicate
let rec removeAll xs pred =
    match xs with
        | [] -> []
        | x::xs when pred x -> removeAll xs pred
        | x::xs -> x :: removeAll xs pred

let removeAllWithFold xs pred =
    List.foldBack (fun t s -> if pred t then s else t::s) xs []


// Experimenting with nested lists

// write a function that takes a nested list and whenever the predicate holds
// adds el to the right of the matching element
let rec addR pred el xs =
    match xs with
        | [] -> []
        | y::ys when pred y -> y :: el :: (addR pred el ys)
        | y::ys -> y :: (addR pred el ys)

type 'a Item =
    | One of 'a
    | Many of list<Item<'a>>

// some examples
let nested = Many [One 1; One 2]
let nested2 = Many [One 1; One 2; Many [One 3; One 2; Many [One 1; One 2]]; One 3; One 2]

exception NonFunzionaUnCazzoException

let rec addRItem2 pred el xs =
    match xs with
        | One o when pred o -> Many [One o; One el]
        | One _ -> xs
        | Many [One o] -> if pred o then Many [One o; One el]
                          else (One o)
        | Many [One o;CAZZO] -> if pred o then Many (Many [One o; One el]::[addRItem2 pred el CAZZO])
                                else (One o)
        | Many (One o::[Many [ms]]) when pred o ->
           Many (Many [One o; One el] :: [addRItem2 pred el ms])
        | Many (One o::([Many [ms]])) ->
           Many (Many [One o] :: [addRItem2 pred el ms])
        | Many (Many [ms]::([Many [ns]])) ->
           Many ((addRItem2 pred el (Many [ms]))::[addRItem2 pred el (Many [ns])])
        | _ -> raise NonFunzionaUnCazzoException
