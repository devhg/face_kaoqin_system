package com.kaoqin.utils;

import com.kaoqin.model.User;

/**
 * 调用Python后端的训练接口
 */
public class TrainRunnable implements Runnable {

    private User user;

    public TrainRunnable(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        String result = RequestPy.train(user.getUsername(), user.getClassName(), "cutImg");
    }
}
