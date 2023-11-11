open Sequence

let main () =
  let sum = Sequence.sumSequences [1; 2; 3; 0; 4; 5; 0; 0; 6; 0]
  in
  List.iter (fun x -> print_int x; print_string " ") sum


let _ = main()
