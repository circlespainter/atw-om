(ns  {{name}}.core
    (:use co.paralleluniverse.fiber.ring.jetty9)
    (:require [co.paralleluniverse.pulsar.core :as pc]
      [ring.middleware.json :as midjson]
      [ring.middleware.resource :as midres]
      [ring.middleware.reload :as midreload]
      [ring.middleware.stacktrace :as midstcktrc]
      [net.cgrand.moustache :as moustache]
      [ring.util.response :as ringres])
    (:import (co.paralleluniverse.fibers Fiber)))

(defn- as-sus! [h] (if (pc/suspendable? h) h (pc/suspendable! h)))

(def ^:private app-routes (moustache/app
                            [] (pc/sfn [_] (Fiber/sleep 100) (ringres/resource-response "index.html" {:root "public"}))
                            ["widgets"] (pc/sfn [_] (Fiber/sleep 100) (ringres/response [{:name "Widget 1"} {:name "Widget 2"}]))))

(def app
  (-> app-routes
      as-sus!
      (midres/wrap-resource "/public")
      as-sus!
      (midjson/wrap-json-body)
      as-sus!
      (midjson/wrap-json-response)))

(defn run [] (run-jetty app {:port 8080}))