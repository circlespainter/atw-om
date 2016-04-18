(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "TODO FIXME: write this!"
  :url "http://example.com/TODO FIXME"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [co.paralleluniverse/comsat-ring-jetty9 "0.7.0"]
                 [ring/ring-json "0.4.0"]
                 [net.cgrand/moustache "1.1.0" :exclusions [org.clojure/clojure ring/ring-core]]
                 [org.clojure/clojurescript "1.8.40"]
                 [org.clojure/core.async "0.2.374"]
                 [cljs-http "0.1.37"]
                 [org.omcljs/om "0.9.0"]
                 [figwheel "0.5.2"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-pdo "0.1.1"]
            [lein-figwheel "0.5.2"]]

  :aliases {"up" ["pdo" "cljsbuild" "auto" "dev," "run"]}

  :main {{name}}.core/run

  :java-agents [[co.paralleluniverse/quasar-core "0.7.4"
                 ; :options "vdmc"
                 ]]


  :jvm-opts ["-Dco.paralleluniverse.fibers.verifyInstrumentation=true" ; TODO Comment out before production
             "-Dco.paralleluniverse.pulsar.instrument.auto=all"
             ; "-Xdebug" "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
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
