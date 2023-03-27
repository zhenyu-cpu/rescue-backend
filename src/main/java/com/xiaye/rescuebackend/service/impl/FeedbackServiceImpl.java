package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.FeedbackMapper;
import com.xiaye.rescuebackend.model.Feedback;
import com.xiaye.rescuebackend.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
}
