# concurrent-solution

## Docker 
  mySql  
  ```
  docker pull mysql  
  docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql_test mysql --character-set-server=utf8 --collation-server=utf8_unicode_ci
  ```
  redis
  ```
  docker pull redis
  docker run --name myredis -d -p 6379:6379 redis
  ```

# Redis

### Lettuce
구현이 간단하다  
spring data redis 를 이용하면 lettuce 가 기본이기때문에 별도의 라이브러리를 사용하지 않아도 된다.  
spin lock 방식이기때문에 동시에 많은 스레드가 lock 획득 대기 상태라면 redis 에 부하가 갈 수 있다.  

### Redisson 
락 획득 재시도를 기본으로 제공한다.  
pub-sub 방식으로 구현이 되어있기 때문에 lettuce 와 비교했을 때 redis 에 부하가 덜 간다.  
별도의 라이브러리를 사용해야한다.  
lock 을 라이브러리 차원에서 제공해주기 떄문에 사용법을 공부해야 한다.  


### 실무에서는 ?  
재시도가 필요하지 않은 lock 은 lettuce 활용  
재시도가 필요한 경우에는 redisson 를 활용  

# Mysql

### Mysql
이미 Mysql 을 사용하고 있다면 별도의 비용없이 사용가능하다.  
어느정도의 트래픽까지는 문제없이 활용이 가능하다.  
Redis 보다는 성능이 좋지않다.  

### Redis
활용중인 Redis 가 없다면 별도의 구축비용과 인프라 관리비용이 발생한다.  
Mysql 보다 성능이 좋다.  
