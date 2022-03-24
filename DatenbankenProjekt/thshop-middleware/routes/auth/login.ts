import express from "express";
const router = express.Router();

import { executeQuery } from "../../scripts/database";

router.get("/", async (req: any, res: any) => {
  try {
    const data = await executeQuery("SELECT * from thshop.users");

    res.status(200).json({ status: "success", message: "", data });

  } catch (error) {
    res.status(400).json({ status: "error", message: error.toString() })
  }
});

export default router;
