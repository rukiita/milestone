package com.example.milestone;

public enum TaskClassification {
  JAVA("Java"),
  PHP("PHP"),
  DATABASE("SQL"),
  SHIKAKU("応用情報"),
  PORTFOLIO("ポートフォリオ"),
  GENERAL("その他"),
  ;

  private final String jpName;

  private TaskClassification(String jpName) {
    this.jpName = jpName;
  }

  public static TaskClassification fromNumber(int number) {
    for (TaskClassification classification : TaskClassification.values()) {
      if (classification.ordinal() == number) {
        return classification;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.jpName;
  }
}