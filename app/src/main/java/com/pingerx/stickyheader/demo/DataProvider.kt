package com.pingerx.stickyheader.demo

/**
 * @author Pinger
 * @since 2019/2/12 17:03
 */
object DataProvider {

    fun getData(): List<String> {
        val data = arrayListOf<String>()
        for (i in 0..50) {
            data.add("每一天都过得美美滴，不用去想明天会怎样，因为即使明天是世界末日，身边也不会有你。")
        }
        return data
    }

}