(ns contact-app-backend.core
  (:gen-class)
  (:require [clojure.java.jdbc :as j])
  (:require [ring.adapter.jetty :as jetty])
  (:require [compojure.core :refer :all])
  )

(def mysql-db {
      :subprotocol "mysql"
      :subname "//127.0.0.1:3306/blogs"
      :user "root"
      :password ""})

(defn contact-list 
  []
  println (j/query mysql-db ["select name from blog_list"]
      :row-fn :name))

(defn create-list 
  []
   (j/insert! mysql-db
      :blog_list {:name "John" :email "testing2@gmail.com" :phone "7985356264" :father "tarun singh" :dob "12-02-1992" :gender "Female"}))

(defn delete-list
  []
  (println (j/delete! mysql-db
      :blog_list ["id = ? " 2])))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Clojure, Hello Ring!"})


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
   (jetty/handler {:port 8092})
)
