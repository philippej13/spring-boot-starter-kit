conn = new Mongo();
db = conn.getDB("starterkit");
db.createUser(
  {
    user: "guest",
    pwd: "guest",
    roles: [
       { role: "readWrite", db: "starterkit" }
    ],
    passwordDigestor : "server"
  }
);
