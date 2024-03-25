import { MongoClient, ServerApiVersion } from "mongodb"
const uri = process.env.MONGODB_CONNECTION_STRING

const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  }
})

export async function login(username, password) {
  const res = await client
        .db("ecommerce")
        .collection("users")
        .findOne({ username, password })
  return [res != null, res]
}
