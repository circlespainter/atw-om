(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "TODO FIXME: write this!"
  :url "http://example.com/TODO FIXME"

  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [co.paralleluniverse/comsat-ring-jetty9 "0.4.0-SNAPSHOT" :exclusions [co.paralleluniverse/pulsar]]
                 [co.paralleluniverse/pulsar "0.7.1-SNAPSHOT"]
                 [ring/ring-json "0.3.1"]
                 [net.cgrand/moustache "1.1.0" :exclusions [org.clojure/clojure ring/ring-core]]
                 [org.clojure/clojurescript "0.0-3308"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljs-http "0.1.35"]
                 [org.omcljs/om "0.8.8"]
                 [figwheel "0.3.3"]]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-pdo "0.1.1"]
            [lein-figwheel "0.3.3"]]

  :aliases {"up" ["pdo" "cljsbuild" "auto" "dev," "run"]}

  :min-lein-version "2.5.0"

  :main {{name}}.core/run

  :java-agents [[co.paralleluniverse/quasar-core "0.7.1-SNAPSHOT"
                 :options "m"
                 ; :options "vdmc"
                 ]]


  :jvm-opts [;"-Dco.paralleluniverse.fibers.verifyInstrumentation=true" ; Don't enable in production
             "-Dco.paralleluniverse.pulsar.instrument.auto=all"
             ; "-Xdebug" "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
             ]

  :source-paths ["src/clj"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/app.js"
                                   :output-dir "resources/public/js/out"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "release"
                        :source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/app.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}]})
