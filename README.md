Respawn anchors datapack
========================

To just install this. Please download the datapack [here](http://gitlab.com/schoentoon/respawn-anchors-worldguard/-/jobs/artifacts/master/raw/datapack/respawn-anchors-worldguard.zip?job=package:datapack).
And simply put this in `./world/datapacks` relative to your minecraft world.
This datapack will override the `minecraft:overworld` dimension type and enable respawn anchors.
If you just want respawn anchors in your overworld you're done here.
This should even work in vanilla minecraft.

Respawn anchors worldguard plugin
=================================

The plugin you can download [here](http://gitlab.com/schoentoon/respawn-anchors-worldguard/-/jobs/artifacts/master/raw/target/RespawnAnchorsWorldGuard-1.0-SNAPSHOT.jar?job=package:plugin).
Simply put this in your `plugins` folder of Paper.
Afterwards you'll want to run `/region flag <region> respawn-anchor-explode deny` to disable the exploding in certain regions.
You'll probably also want to run `/region flag __global__ respawn-anchor-explode deny` in the nether.

If you're curious why I made this in the first place and how I got to the implementation that I did, please head over to [my blog](https://blog.schoentoon.blue/posts/minecraft-respawn-anchors-in-overworld-for-minigames/).