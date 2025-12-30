(defproject using-clojure-with-aoc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}

  :uberjar-merge-with
  {"META-INF/services/java.net.spi.InetAddressResolverProvider"
   [slurp (fn [a b] (str a "\n" b)) spit]}

  :dependencies [[org.clojure/clojure "1.12.2"]
                 [org.slf4j/slf4j-api "2.0.9"]
                 [ch.qos.logback/logback-classic "1.5.18"]
                 [org.clojure/core.match "1.1.0"]
                 [org.clojure/tools.cli "1.2.245"]
                 [criterium "0.4.6"]]

  :plugins      [[lein-javadoc "0.3.0"]]

  :target-path "target/%s"

  :source-paths ["src"]

  :resource-paths ["resources"]

  )
