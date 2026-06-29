(ns solid)


;; --- Source code ---

(defn pay [employee pay-date]
  (let [is-payday?    (:is-payday employee)
        calc-pay      (:calc-pay employee)
        send-paycheck (:send-paycheck employee)]
    (when (is-payday? pay-date)
      (let [paycheck (calc-pay)]
        (send-paycheck paycheck)))))


;; --- Test code ---

(defn test-is-payday? [employee-data pay-date]
  true)

(defn test-calc-pay [employee-data]
  (:pay employee-data))

(defn test-send-paycheck [employee-data paycheck]
  (format "Send %d to %s at %s"
          paycheck
          (:name employee-data)
          (:address employee-data)))

(defn make-test-employee [name address pay]
  (let [employee-data {:name    name
                       :address address
                       :pay     pay}
        employee      {:employee-data employee-data
                       :is-payday     (partial test-is-payday? employee-data)
                       :calc-pay      (partial test-calc-pay employee-data)
                       :send-paycheck (partial test-send-paycheck employee-data)}]
    employee))

(defn make-later-employee [name address pay]
  (let [employee-data {:name    name
                       :address address
                       :pay     pay}
        employee      {:employee-data employee-data
                       :is-payday     (partial (fn [_ _] :tomorrow) employee-data)
                       :calc-pay      (partial test-calc-pay employee-data)
                       :send-paycheck (partial test-send-paycheck employee-data)}]
    employee))

(assert (= "Send 100 to Alice at 123 Main St"
           (pay (make-test-employee "Alice" "123 Main St" 100) :now)))

(assert (= nil
           (pay (make-later-employee "Bob" "456 Elm St" 200) :not-payday)))
