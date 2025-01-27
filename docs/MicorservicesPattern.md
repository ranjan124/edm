# Microservices Design Patterns
![img.png](img.png)

## Database-per-service pattern
- Each service has its own database
![img_1.png](img_1.png)
- Advantages
    - Loose coupling
    - Independent Scaling
    - Faster development
    - Resilience, Fault tolerance
    - freedom to choose database
    - prevent unauthorized access to database from other microservices
- Challenges
  - Cross service queries
  ![img_2.png](img_2.png)
  - API composition pattern 
    - Invoke microservices and combine their responses
    - Recommended for red or query operations
    - API Composer : executes the query by interactions with microservices
    - Provider Service: managing part of data required by the query
    - Disadvantages
      - Increased latency
      - Complex logic
      - Error Handling
      - Data Consistency
      - Limited scalability
      - Dependencies on service availability
      - Testing challenges
  - ![img_3.png](img_3.png)
  - 