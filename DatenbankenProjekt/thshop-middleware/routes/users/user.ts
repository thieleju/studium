import express from "express";
const router = express.Router();

import axios from "axios";

import { verifyToken } from "../../scripts/authentication";

router.post("/", verifyToken, async (req: any, res: any) => {
  try {
    const user = await axios.get("https://api.github.com/user", {
      headers: {
        Authorization: "Bearer " + req.body.access_token,
      }
    });

    res.status(200).json({
      status: "success",
      user: user.data
    });
  } catch (error) {
    res.status(400).json({
      status: "error",
      message: error.toString()
    })
  }
});

export default router;
