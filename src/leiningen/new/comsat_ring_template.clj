(ns leiningen.new.comsat-ring-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]))

(def render (renderer "comsat-ring-template"))

(defn comsat-ring-template [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
      ["project.clj" (render "project.clj" data)]
      ["src/clj/{{sanitized}}/core.clj" (render "core.clj" data)]
      ["src/cljs/{{sanitized}}/core.cljs" (render "core.cljs" data)]
      ["resources/public/index.html" (render "index.html" data)]
      ["resources/META-INF/suspendables" (render "suspendables" data)]
      [".gitignore" (render "gitignore" data)]
      [".travis.yml" (render "travis.yml" data)]
      ["README.md" (render "README.md" data)])))