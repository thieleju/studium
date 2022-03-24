import express from "express";
const router = express.Router();

import axios from "axios";

import { verifyToken } from "../../scripts/authentication";

interface AppItem {
  title: string;
  name: string;
  icon: string;
}

router.get("/", verifyToken, async (req: any, res: any) => {

  const apps: AppItem[] = [
    {
      title: "Home",
      name: "home",
      icon: "mdi-home",
    },
    {
      title: "Profile",
      name: "profile",
      icon: "mdi-account",
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
