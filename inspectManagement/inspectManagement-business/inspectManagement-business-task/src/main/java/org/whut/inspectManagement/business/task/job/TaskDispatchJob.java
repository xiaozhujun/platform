package org.whut.inspectManagement.business.task.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.task.service.InspectTaskService;
import org.whut.platform.fundamental.logger.PlatformLogger;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-7-10
 * Time: 上午12:25
 * To change this template use File | Settings | File Templates.
 */
public class TaskDispatchJob{

    public final PlatformLogger logger = PlatformLogger.getLogger(TaskDispatchJob.class);

    @Autowired
    private InspectTaskService inspectTaskService;

    public void execute(){
        logger.info("TaskDispatchJob is executed!");
        inspectTaskService.dispatchTask();
    }
}
