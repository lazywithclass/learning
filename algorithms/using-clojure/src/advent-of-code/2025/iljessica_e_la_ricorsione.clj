(ns advent-of-code.2025.iljessica-e-la-ricorsione)

(defn jmap [f l]
  (loop [[head & tail] l
         acc '[]]
    (if (nil? head)
      acc
      (recur tail (conj acc (f head))))))

(jmap #(* 2 %) '(1 2 3 4))


(defn test [[head & tail]]
  (println head tail))

(test '(1 2 3 4))
