package entity;

import tk.mybatis.mapper.genid.GenId;

public class IdWorkSelfUse implements GenId<Long>{
    IdWorker idWorker =new IdWorker(0,0);
    @Override
    public Long genId(String table, String column) {
        // 这里自己采用分布式id生成算法实现就好了。
        return idWorker.nextId();
    }
}



