#r "nuget:FsCheck"

open FsCheck


module Exercise =

    let rec suf lls =
        match lls with
            | [] -> []
            | _::ls -> suf ls @ [lls]

    let reverse ls = List.fold (fun s l -> l::s) [] ls

    let sufJ input =
        let rec prefixs rev =
            match rev with
                | [] -> []
                | [x] -> [[x]]
                | head :: tail -> (head :: tail) :: prefixs tail
        prefixs input |> List.rev

    let sameListProp ls =
        let hs = List.map (fun ls -> List.head ls) (suf ls)
        reverse hs = ls

//    do Check.Quick sameListProp

    // JessiBit's approach to coding a pref function
    let rec pref lls =
        match lls with
            | [] -> []
            | head :: xs ->
                [head] :: (List.map (fun x -> head :: x) (pref xs))

(*
My explanation since I don't get it :(

[head] :: (List.map (fun x -> head :: x) (pref xs))
          ^
          map through all the other prefixes, distributing the head
          onto each one of those
^
conses the head onto the list of the prefixes, creating a new prefix

[1] :: (map (x -> 1 :: x) (pref [2;3]))
[1] :: (map (x -> 1 :: x) ([2] :: (map (x -> 2 :: x) (pref [3]))))
[1] :: (map (x -> 1 :: x) ([2] :: (map (x -> 2 :: x) ([3] :: (map (x -> 3 :: x) (pref []))))))

- end of recursion -

[1] :: (map (x -> 1 :: x) ([2] :: (map (x -> 2 :: x) ([3] :: (map (x -> 3 :: x) []))))
[1] :: (map (x -> 1 :: x) ([2] :: (map (x -> 2 :: x) ([3] :: []))))
[1] :: (map (x -> 1 :: x) ([2] :: (map (x -> 2 :: x) [[3]])))
[1] :: (map (x -> 1 :: x) ([2] :: [[2;3]]))
[1] :: (map (x -> 1 :: x) [[2];[2;3]])
[1] :: [[1;2];[1;2;3]]
[[1];[1;2];[1;2;3]]
*)
