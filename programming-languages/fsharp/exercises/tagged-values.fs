module TaggedValues =

    type figura =
        | Rettangolo of  float * float
        | Quadrato   of  float
        | Triangolo  of  float * float

    let area fig =
        match fig with
            | Rettangolo(b,h) -> b * h
            | Quadrato l      -> l * l
            | Triangolo(b,h)  -> (b * h) / 2.0

    let areaOpt fig =
        match fig with
            | Rettangolo(b, h) when b < 0 || h < 0 -> None
            | Quadrato l when l < 0 -> None
            | Triangolo(b, h) when b < 0 || h < 0 -> None
            | _ -> Some (area fig)

    let printArea fig =
        match areaOpt fig with
            | None -> "la figura non e' ben definita"
            | Some a -> "area: " + string a

    let sommaArea (fig1, fig2) =
        match areaOpt fig1, areaOpt fig2 with
            | (None, _) | (_, None) -> None
            | (Some area1, Some area2) -> Some (area1 + area2)

    let rec sommaAreaList figs =
        match figs with
            | [] -> 0.0
            | fig::figs -> match (areaOpt fig) with
                | None -> 0.0 + sommaAreaList figs
                | Some area -> area + sommaAreaList figs

// for some reason I had to put this into a module, without it
// it won't compile... I am not sure why
module Wat =
    type point = float * float

    type shape =
        | Point     of point
        | Circle    of point * float
        | Rectangle of point * point

    let area shape =
        match shape with
            | Point (_,_) -> 0.0
            | Circle (_, r) -> r * r *  System.Math.PI
            | Rectangle ((x1, y1), (x2, y2)) -> (x2 - x1) * (y2 - y1)

    let center shape =
        match shape with
            | Point point | Circle (point, _) -> point
            | Rectangle ((x1, y1), (x2, y2)) -> ((x2 - x1) / 2.0), ((y2 - y1) / 2.0)
