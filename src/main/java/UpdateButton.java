    public class UpdateButton implements Runnable {
        private TestFrom testFrom;
        public UpdateButton(TestFrom testFrom) {
            this.testFrom = testFrom;
        }
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(5000);
                    testFrom.getLock().lock();
                    try {
                        testFrom.getLabel1().setText("Text Changed By Thread " + testFrom.getAutoCount());
                        testFrom.setAutoCount(testFrom.getAutoCount() + 1);
                    } finally {
                        testFrom.getLock().unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }