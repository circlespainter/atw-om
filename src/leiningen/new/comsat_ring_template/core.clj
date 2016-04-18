(ns {{name}}.core
    (:use co.paralleluniverse.fiber.ring.jetty9)
    (:require
      [ring.middleware.json :as midjson]
      [ring.middleware.resource :as midres]
      [net.cgrand.moustache :as moustache]
      [ring.util.response :as ringres])
    (:import (co.paralleluniverse.fibers Fiber)))

(def ^:private app-routes (moustache/app
                            [] (fn [_] (Fiber/sleep 100) (ringres/resource-response "index.html" {:root "public"}))
                            ["widgets"] (fn [_] (Fiber/sleep 100) (ringres/response [{:name "Widget 1"} {:name "Widget 2"}]))))

(def app
  (-> app-routes
      (midres/wrap-resource "/public")
      (midjson/wrap-json-body)
      (midjson/wrap-json-response)))

(defn run [] (run-jetty app {:port 8080}))
