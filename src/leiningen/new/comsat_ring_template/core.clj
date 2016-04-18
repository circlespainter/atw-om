(ns {{name}}.core
    (:use co.paralleluniverse.fiber.ring.jetty9)
    (:require
      [co.paralleluniverse.pulsar.core :refer [sfn defsfn suspendable!]]
      [ring.middleware.json :as midjson]
      [ring.middleware.resource :as midres]
      [net.cgrand.moustache :as moustache]
      [ring.util.response :as ringres])
    (:import (co.paralleluniverse.fibers Fiber)))

(def ^:private app-routes (moustache/app
                            [] (sfn [_] (Fiber/sleep 100) (ringres/resource-response "index.html" {:root "public"}))
                            ["widgets"] (sfn [_] (Fiber/sleep 100) (ringres/response [{:name "Widget 1"} {:name "Widget 2"}]))))

(def app
  (-> app-routes
      suspendable!
      (midres/wrap-resource "/public")
      suspendable!
      (midjson/wrap-json-body)
      suspendable!
      (midjson/wrap-json-response)))

(defn run [] (run-jetty app {:port 8080}))
