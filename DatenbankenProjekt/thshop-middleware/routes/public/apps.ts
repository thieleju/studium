import express from "express";
const router = express.Router();

router.get("/", (req: any, res: any) => {
  const apps = [{
    title: "Start",
    name: "start",
    icon: "mdi-home",
  },
  {
    title: "About",
    name: "about",
    icon: "mdi-information",
  }]

  res.status(200).json({ status: "success", apps });
});

export default router;
