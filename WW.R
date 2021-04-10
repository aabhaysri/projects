library(tidyverse)



ww <- read.csv("WW.csv")
ww <- ww[, c(2, 3, 5, 7, 4, 6)]
names(ww) <- c("Stress",
               "Meetings",
               "EOD",
               "Predef",
               "Classes",
               "Activities")
qNames <- c(
  "I Have More Stress on Wellness Wednesdays.",
  "I Have More Meetings on Wellness Wednesdays.",
  "Wellness Wednesday Work Should Be Due Start of Next Class",
  "School-Related EC Activities Should Happen During a Predefined Time",
  "Number of Classes that have WW Work and HMW",
  "Activities for WW Wednesdays"
)

createFactor <- function(df, index) {
  x <-
    factor(df[, index],
           levels = c(
             "Strongly Disagree",
             "Disagree",
             "Neutral",
             "Agree",
             "Strongly Agree"
           ))
  x <- x[!is.na(x)]
  return(x)
}

createWeightage <- function(x) {
  total <- 0
  tableX <- table(x)
  total <-
    total + as.numeric(tableX[1]) + as.numeric(tableX[2]) * 2 +
    as.numeric(tableX[3]) * 3 + as.numeric(tableX[4]) * 4 + as.numeric(tableX[5]) * 5
  return(total/length(x))
}

createActFactor <- function(df, index) {
  act <- df[, index]
  for (i in 1:length(act)) {
    act[i] <-
      str_remove(act[i], pattern = fixed(" (Kahoot, Quizlet Live, etc.)"))
    act[i] <-
      str_remove(act[i], pattern = fixed(" (Hangman, Bingo, Puzzles)"))
  }
  str <- str_split(act, pattern = ", ")
  str <- as.vector(unlist(str))
  str <-
    factor(
      str,
      levels = c(
        "Trivia",
        "Games",
        "Individual Activities",
        "Group Activities with Friends"
      )
    )
  str <- str[!is.na(str)]
  return(str)
}

createPlot <- function(v, index) {
  df <- as.data.frame(v)
  plot <- ggplot(data = df) +
    
    geom_bar(mapping = aes(x = df[, 1]), fill = "#4169e1") +
    
    labs(title = qNames[index], y = "Frequency",
         x = "Responses") +
    
    theme_gray() +
    
    theme(
      plot.title = element_text(
        color = "black",
        face = "bold",
        size = 12,
        hjust = .5,
        family = "Times"
      ),
      axis.title.x = element_text(
        color = "black",
        size = 16,
        family = "Times"
      ),
      axis.title.y = element_text(
        color = "black",
        size = 16,
        family = "Times"
      )
    )
  ggsave(
    paste0(names(ww)[index], ".png"),
    plot = last_plot(),
    width = 16,
    height = 9,
    units = "cm"
  )
}
# createPlot(createActFactor(ww, 6), 6)
for(i in 1:4){
  x <- createFactor(ww, i)
  print(names(ww)[i])
  print(createWeightage(x))
}