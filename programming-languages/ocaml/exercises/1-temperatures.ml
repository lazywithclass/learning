module type TEMP_SCALE = sig
  val to_kelvin : float -> float
  val of_kelvin : float -> float
end

module Convert(T1: TEMP_SCALE) (T2: TEMP_SCALE) = struct
  let convert (x: float) : float =
    let kelvin = T1.to_kelvin x in
    T2.of_kelvin kelvin
end

module Celsius = struct
  let to_kelvin c = c +. 273.15
  let of_kelvin k = k -. 273.15
end

module Fahrenheit = struct
  let to_kelvin f = (f -. 32.0) *. 5.0 /. 9.0 +. 273.15
  let of_kelvin k = (k -. 273.15) *. 9.0 /. 5.0 +. 32.0
end

module Rankine = struct
  let to_kelvin f = f *. 5.0 /. 9.0
  let of_kelvin k = k *. 9.0 /. 5.0
end

module C_to_F = Convert(Celsius)(Fahrenheit)
module C_to_R = Convert(Celsius)(Rankine)

let () =
  let c_value = 25.0 in
  let f_value = C_to_F.convert c_value in
  let r_value = C_to_R.convert c_value in
  Printf.printf "%.2f°C is %.2f°F\n" c_value f_value;
  Printf.printf "%.2f°C is %.2f°R\n" c_value r_value
