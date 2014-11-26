package com.krupa


class PiechartController {

    def index = {

        // create the data for the pie chart
        def slices = [
            [label:"One", percent:43.2],
            [label:"Two", percent:10.0],
            [label:"Three", percent:27.5],
            [label:"Four", percent:17.5],
            [label:"Five", percent:11.0],
            [label:"Six", percent:19.4]
        ]

        // load the data into a dataset
        def dataset = new DefaultPieDataset();
            slices.each { slice ->
            dataset.setValue(slice.label, slice.percent)
         }

         // create the pie chart and stream it back to the client
         def chart = ChartFactory.createPieChart("Pie Chart Demo 1", dataset, true, true, false)
         EncoderUtil.writeBufferedImage(chart.createBufferedImage(800, 600), "png", response.getOutputStream())
    }

}