import express from "express";
const router = express.Router();

interface AppItem {
  title: string;
  name: string;
  icon: string;
}

router.get("/", async (req: any, res: any) => {

  const apps: AppItem[] = [
    {
      title: "Home",
      name: "start",
      icon: "mdi-home",
    },
    {
      title: "About",
      name: "about",
      icon: "mdi-information",
    },
  ];

  res.status(200).json({ status: "success", apps });
});

export default router;
