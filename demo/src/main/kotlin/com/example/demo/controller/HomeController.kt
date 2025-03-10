package com.example.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.slf4j.LoggerFactory
import java.util.UUID
import java.time.temporal.ChronoUnit

@Controller
class HomeController {
    
    private val logger = LoggerFactory.getLogger(HomeController::class.java)
    
    @GetMapping("/")
    fun home(model: Model): String {
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        
        // Log the time before displaying
        logger.info("Accessing home page. Current time: $currentTime")
        
        model.addAttribute("currentTime", currentTime)
        return "index"
    }
    
    @GetMapping("/tweets")
    fun tweets(model: Model): String {
        logger.info("Generating AI tweets")
        
        val tweets = generateAITweets()
        model.addAttribute("tweets", tweets)
        
        return "tweets"
    }
    
    private fun generateAITweets(): List<Tweet> {
        val tweetContents = listOf(
            "Just finished training a new language model with 10x better reasoning abilities! #AI #MachineLearning",
            "AI ethics should be at the forefront of development. We can't afford to build systems without considering societal impact.",
            "Implemented a neural network that can generate music indistinguishable from human composers. The future is now! #AIMusic",
            "Fascinating paper on using reinforcement learning from human feedback to align AI systems with human values. Link in bio!",
            "Sometimes I wonder if my AI assistant understands me better than my colleagues do. #ModernLife #AI",
            "Breaking: New benchmark shows multimodal models achieving human-level performance on visual reasoning tasks! #ComputerVision #AI",
            "The gap between narrow AI and AGI remains vast. Let's be realistic about our timelines while still pushing boundaries.",
            "Excited to announce our new AI-powered tool that helps developers write cleaner code faster. #AI #Coding",
            "AI in healthcare is revolutionizing diagnostics and treatment plans. The potential is enormous! #HealthTech #AI",
            "Exploring the intersection of AI and art. Can machines truly be creative? #AICreativity #Art"
        )
        
        val authors = listOf(
            "AIResearcher",
            "DeepMindLab",
            "NeuralNetNinja",
            "EthicalAI",
            "TechFuturist",
            "AIEngineer",
            "DataScientist",
            "RoboticsExpert",
            "MLEnthusiast",
            "AICreator"
        )
        
        val now = LocalDateTime.now()
        
        return tweetContents.mapIndexed { index, content ->
            Tweet(
                id = UUID.randomUUID().toString(),
                content = content,
                timestamp = now.minusHours((1..48).random().toLong()),
                author = authors[index % authors.size],
                handle = "@" + authors[index % authors.size].lowercase(),
                likes = (10..5000).random(),
                retweets = (5..1000).random(),
                comments = (2..200).random()
            )
        }
    }
}

data class Tweet(
    val id: String, 
    val content: String, 
    val timestamp: LocalDateTime,
    val author: String? = null,
    val handle: String? = null,
    val likes: Int = 0,
    val retweets: Int = 0,
    val comments: Int = 0
)