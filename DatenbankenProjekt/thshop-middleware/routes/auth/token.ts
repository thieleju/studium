import express from "express";
const router = express.Router();

import axios from "axios";
import jwt from "jsonwebtoken";

const tokenExpire: string = process.env.TOKEN_EXPIRE;

router.post("/", async (req, res) => {
  try {
    if (typeof req.body.access_token !== "undefined") {
      // split token and use it to get user infos from github
      const user = await axios.get("https://api.github.com/user", {
        headers: {
          Authorization: "Bearer " + req.body.access_token,
        },
      });
      // sign userdata token
      const token = jwt.sign({ login: user.data.login }, process.env.JWT_SECRET, {
        expiresIn: tokenExpire,
      });

      // send token
      res.status(200).json({
        status: "success",
        tokenJwt: token,
        tokenGitHub: req.body.access_token
      });
    } else throw new Error("Invalid authorization header");
  } catch (error) {
    res.status(401).json({ status: "error", error: error.toString() });
  }
});

export default router;
