package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        try {
            service.callThrow();
        } catch (MyCheckedException e) {
            log.info("예외 처리, message={}", e.getMessage(), e);
        }
    }

    /***
     * Exception을 상속받은 예외는 체크 예외가 된다.
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /***
     * Checked 예외는 예외를 잡아서 처리하거나, 던지거나 둘 중 하나는 해야함
     */
    static class Service {
        Repository repository = new Repository();
        /***
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /***
         * 예외를 던지는 코드
         * @throws MyCheckedException
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("Ex");
        }
    }
}
