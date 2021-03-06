(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [x] (< x n)))

(defn equal-to [n]
  (fn [x] (== x n)))

(defn set->predicate [a-set]
  (fn [s] (contains? a-set s)))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or (pred1 x) (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (every? whitespace? string))

(defn has-award? [book award]
  ((set->predicate (:awards book)) award))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (every? identity (map (fn [a] (has-award? book a)) awards)))

(defn my-some [pred a-seq]
  (let [result (filter pred a-seq)]
        (if (not (empty? result))
          (pred (first result))
          false)))

(defn my-every? [pred a-seq]
  (if (empty? a-seq)
    true
    (let [result (filter pred a-seq)]
      (if (= (count result) (count a-seq))
        true
        false))))

(defn prime? [n]
  (let [pred (fn [x] (= 0 (mod n x)))]
    (not (some pred (range 2 n)))))
        
        

;^^
