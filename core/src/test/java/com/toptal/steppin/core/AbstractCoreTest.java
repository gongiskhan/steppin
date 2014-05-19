package com.toptal.steppin.core;

import com.toptal.steppin.core.config.CoreConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by ggomes on 11/05/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
@TransactionConfiguration(defaultRollback=true)
public abstract class AbstractCoreTest extends AbstractTransactionalJUnit4SpringContextTests {}
