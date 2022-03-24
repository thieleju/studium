import express from "express";
const router = express.Router();

router.get("/", (req: any, res: any) => {
  res.status(200).json({ status: "success", message: "" });
});

export default router;
