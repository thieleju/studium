import express from "express";
import dotenv from "dotenv";
import cors from "cors";
import helmet from "helmet";

const app = express();

// set dynamic environment variables
// NODE_ENV is set in the npm scripts
dotenv.config({ path: "./.env." + process.env.NODE_ENV });

const port: number = Number(process.env.SERVER_PORT);
const origin: string = process.env.CORS_ORIGIN;

// secure express app
app.use(helmet());

// protect with cors
app.use(
  cors({
    methods: ["GET", "POST"],
    origin,
  })
);

// middleware able to get requestors ip by req.ip
app.set("trust proxy", true);
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// check if provided data is a valid json to catch unhandled errors
app.use((err: any, req: any, res: any, next: any) => {
  if (err)
    res.status(400).json({
      status: "error",
      message: "Invalid request!",
      error: err.toString(),
    });
});

app.get("/", (req, res) => {

  res.status(200).json({
    success: true,
    message: "API Online!",
    authorization: req.headers.authorization,
  });
});

// import routes
import authRouter from "./routes/auth";
import usersRouter from "./routes/users";
import publicRouter from "./routes/public";
app.use("/auth", authRouter);
app.use("/users", usersRouter);
app.use("/public", publicRouter);

// Create server object
const server = app.listen(port, () => {
  if (process.env.NODE_ENV === "production") {
    // tslint:disable-next-line:no-console
    console.log("Running in production on port " + port);
  } else {
    // tslint:disable-next-line:no-console
    console.log("Running in development mode on port " + port);
  }
});
