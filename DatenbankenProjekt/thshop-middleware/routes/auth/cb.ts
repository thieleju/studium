import express from "express";
import axios from "axios";

const router = express.Router();

const clientId: string = process.env.CLIENT_ID;
const clientSecret: string = process.env.CLIENT_SECRET;

router.get("/", async (req, res) => {
  try {
    // use code query parameter to get access_token from github
    const data = await axios.post(
      "https://github.com/login/oauth/access_token",
      {
        client_id: clientId,
        client_secret: clientSecret,
        code: req.query.code,
      }
    );
    // redirect to callback component with access token in query
    res.redirect("http://localhost:3000/cb?" + data.data);
  } catch (e) {
    res.status(401).json({ status: "error", message: e.toString() });
  }
});

export default router;
